<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductGallery extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $productGallery = $this->table('product_gallery', ['signed' => false]);
        $productGallery->addColumn('product_id', 'biginteger', ['signed' => false])
            ->addColumn('file_path', 'string', ['limit' => 255])
            ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
