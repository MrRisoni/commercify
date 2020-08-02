<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductDescription extends AbstractMigration
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
        $productDescr= $this->table('product_description', ['signed' => false]);
        $productDescr->addColumn('product_id', 'biginteger', ['signed' => false])
            ->addColumn('language_id', 'biginteger', ['signed' => false])
            ->addColumn('title', 'string', ['limit' => 255])
            ->addColumn('descr', 'string' )
            ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('language_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
