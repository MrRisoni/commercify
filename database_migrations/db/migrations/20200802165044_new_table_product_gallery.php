<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductGallery extends AbstractMigration
{

    public function change(): void
    {
        $productGallery = $this->table('product_gallery', ['signed' => false]);
        $productGallery->addColumn('product_id', 'biginteger', ['signed' => false])
            ->addColumn('file_path', 'string', ['limit' => 255])
            ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
