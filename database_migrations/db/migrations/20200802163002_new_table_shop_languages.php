<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopLanguages extends AbstractMigration
{

    public function change(): void
    {
        $shopLangs = $this->table('shop_languages', ['signed' => false]);
        $shopLangs->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('language_id', 'biginteger', ['signed' => false])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('language_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
